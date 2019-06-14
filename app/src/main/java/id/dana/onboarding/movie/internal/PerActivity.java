package id.dana.onboarding.movie.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component
 */

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PerActivity, v 0.1 2019-06-14 14:11 by Derayan Bima A
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
