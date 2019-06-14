package id.dana.onboarding.domain.repository;

import java.util.List;

import id.dana.onboarding.domain.Video;
import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version VideoTrailerRepository, v 0.1 2019-06-14 10:59 by Derayan Bima A
 */
public interface VideoTrailerRepository {

    Observable<List<Video>> getVideoTrailers(int movieId);
}
