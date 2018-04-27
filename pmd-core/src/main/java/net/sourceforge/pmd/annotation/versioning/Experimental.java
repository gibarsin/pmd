/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.annotation.versioning;

/**
 * New APIs marked with this annotation at the class or method level are subject to change in any following release
 * (patch, minor or major) or merge with the code base, and can be modified in any way (including but not extensive to
 * behaviour, signature) or even removed.
 * <p>
 * It is highly recommended to only make use of the API if you are a developer of it and of the features that it may
 * be used for (i.e. the consumers of the API).
 * </p>
 * <p>
 * If it is decided that an API should have the annotation removed, after the release or merge to the code base which
 * includes that removal the annotation cannot be added again to the API, and a major release is necessary to approve
 * any changes to it.
 * </p>
 * <p>
 * It is recommended to switch from this annotation to {@link Beta} to ensure to its consumers that the API will not be
 * modified, but may still present instability when used.
 * </p>
 * <p>
 * As a consequence of this definition, any change to the API while using this annotation is not a breaking API change,
 * meaning that any use of it, although the consumer may not use this annotation, it is going to have to adjust to
 * those changes internally.
 * </p>
 */
public @interface Experimental {
}

