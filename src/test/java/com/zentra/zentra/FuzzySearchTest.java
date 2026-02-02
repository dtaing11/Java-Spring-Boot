package com.zentra.zentra;
import com.zentra.zentra.helper.FuzzySearch;
import com.zentra.zentra.helper.NameUUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class FuzzySearchTest {

    // --- minimal helper builder (adjust if your NameUUID constructor differs) ---
    private static NameUUID nu(String name) {
        // If NameUUID requires UUID, swap this to your real constructor:
        // return new NameUUID(name, UUID.randomUUID());
        return new NameUUID(name, UUID.randomUUID());
    }


    @Test
    void exactMatchShouldComeFirst() {
        List<NameUUID> input = List.of(
                nu("john"),
                nu("jon"),
                nu("jane"),
                nu("johnny")
        );

        List<NameUUID> out = FuzzySearch.fuzzySearch("john", input);
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

        assertEquals("john", out.get(0).getName(), "Exact match should rank first");
    }

    @Test
    void resultsShouldBeSortedByIncreasingDistance() {
        List<NameUUID> input = List.of(
                nu("cat"),
                nu("cut"),
                nu("cast"),
                nu("dog"),
                nu("cats")
        );

        List<NameUUID> out = FuzzySearch.fuzzySearch("cat", input);
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

        // verify monotonic non-decreasing distance
        for (int i = 0; i < out.size() - 1; i++) {
            int d1 = levenshtein("cat", out.get(i).getName());
            int d2 = levenshtein("cat", out.get(i + 1).getName());
            assertTrue(d1 <= d2, "Distances should be non-decreasing: " + d1 + " then " + d2);
        }
    }

    @Test
    void emptyQueryShouldReturnSortedByNameDistanceFromEmpty() {
        List<NameUUID> input = List.of(
                nu("a"),
                nu(""),
                nu("abc"),
                nu("ab")
        );


        List<NameUUID> out = FuzzySearch.fuzzySearch("", input);
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

        // empty string should come first (distance 0)
        assertEquals("", out.get(0).getName());
    }

    @Test
    void realWrodlTest() {
        List<NameUUID> input = List.of(
                nu("a"),
                nu(""),
                nu("abc"),
                nu("ab")
        );


        List<NameUUID> out = FuzzySearch.fuzzySearch("", input);
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

        // empty string should come first (distance 0)
        assertEquals("", out.get(0).getName());
    }

    @Test
    void emptyListShouldReturnEmpty() {
        List<NameUUID> out = FuzzySearch.fuzzySearch("anything", List.of());
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }
        assertTrue(out.isEmpty());
    }


    @Test
    void shouldNotThrowWhenQueryLongerThanList() {
        List<NameUUID> input = List.of(
                nu("a"),
                nu("b")
        );

        assertDoesNotThrow(() -> FuzzySearch.fuzzySearch("thisQueryIsLong", input));
    }


    // --- Local levenshtein for verification in tests ---
    // (So tests don't depend on private method access.)
    private static int levenshtein(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[n][m];
    }

    private static String randomString(Random r, int minLen, int maxLen) {
        int len = minLen + r.nextInt(maxLen - minLen + 1);
        StringBuilder sb = new StringBuilder(len);

        boolean hasLetter = false;

        for (int i = 0; i < len; i++) {
            int roll = r.nextInt(30);
            char c;

            if (roll == 0) {
                c = ' ';
            } else {
                c = (char) ('a' + r.nextInt(26));
                hasLetter = true;
            }

            sb.append(c);
        }

        // ensure at least one letter
        if (!hasLetter) {
            sb.setCharAt(0, (char) ('a' + r.nextInt(26)));
        }

        return sb.toString().trim();
    }


    /**
     * Stress-ish test: big candidate list + paragraph query + repeated runs.
     * Disabled by default so it doesn't slow down every test run.
     */
   // @Disabled("Performance/stress test - enable manually when needed")
    @Test
    void fuzzySearch_paragraphQuery_stressTest() {
        Random r = new Random(123);

        // "Paragraph-like" query (~300-600 chars)
        String query = randomString(r, 350, 550);

        // Big candidate list (tune these numbers)
        int candidateCount = 5_000;   // try 20_000 if you want to push it
        List<NameUUID> candidates = new ArrayList<>(candidateCount);

        // Create candidates with varying lengths
        for (int i = 0; i < candidateCount; i++) {
            String name = randomString(r, 5, 40);
            candidates.add(nu(name));
        }

        // Ensure there is an exact-ish match somewhere so output isn't trivial
        candidates.set(candidateCount / 2, nu(query.substring(0, Math.min(25, query.length()))));
        List<NameUUID> out = FuzzySearch.fuzzySearch(query, candidates);
        // Warmup (helps reduce JVM cold-start noise)
        for (int i = 0; i < 3; i++) {

            assertEquals(candidateCount, out.size());
        }
        for(int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

        // Timed runs
        int runs = 5;
        long start = System.nanoTime();

        List<NameUUID> last = null;
        for (int i = 0; i < runs; i++) {
            last = FuzzySearch.fuzzySearch(query, candidates);
        }

        long end = System.nanoTime();
        double totalMs = (end - start) / 1_000_000.0;
        double avgMs = totalMs / runs;

        assertNotNull(last);
        assertEquals(candidateCount, last.size());

        System.out.println("FuzzySearch stress test:");
        System.out.println("  candidates = " + candidateCount);
        System.out.println("  query chars = " + query.length());
        System.out.println("  runs = " + runs);
        System.out.printf("  total = %.2f ms%n", totalMs);
        System.out.printf("  avg   = %.2f ms/run%n", avgMs);

        // Optional: add a very generous upper bound
        // ⚠️ Keep it loose to avoid flaky CI failures.
        // assertTrue(avgMs < 2000.0, "Too slow: " + avgMs + " ms/run");
    }
}
