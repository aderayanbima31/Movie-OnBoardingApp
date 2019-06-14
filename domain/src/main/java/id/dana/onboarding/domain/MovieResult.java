package id.dana.onboarding.domain;

import java.util.List;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieResult, v 0.1 2019-06-14 10:53 by Derayan Bima A
 */
public class MovieResult {

    private List<Movie> movies = null;

    private int page;

    private int totalPages;

    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return movies;
    }

    public void setResults(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
