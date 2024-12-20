package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for jUnitEngine (org.junit.jupiter:junit-jupiter-engine)
     * with versionRef 'junitVersion'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJUnitEngine() {
            return create("jUnitEngine");
    }

        /**
         * Creates a dependency provider for junit (org.junit.jupiter:junit-jupiter)
     * with versionRef 'junitVersion'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("junit");
    }

        /**
         * Creates a dependency provider for kotlinCoroutines (org.jetbrains.kotlinx:kotlinx-coroutines-core)
     * with versionRef 'kotlin.couroutines.version'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKotlinCoroutines() {
            return create("kotlinCoroutines");
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class VersionAccessors extends VersionFactory  {

        private final CatalogUpdateVersionVersionAccessors vaccForCatalogUpdateVersionVersionAccessors = new CatalogUpdateVersionVersionAccessors(providers, config);
        private final KotlinVersionAccessors vaccForKotlinVersionAccessors = new KotlinVersionAccessors(providers, config);
        private final UpdateVersionVersionAccessors vaccForUpdateVersionVersionAccessors = new UpdateVersionVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: junitVersion (5.10.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunitVersion() { return getVersion("junitVersion"); }

        /**
         * Returns the group of versions at versions.catalogUpdateVersion
         */
        public CatalogUpdateVersionVersionAccessors getCatalogUpdateVersion() {
            return vaccForCatalogUpdateVersionVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.kotlin
         */
        public KotlinVersionAccessors getKotlin() {
            return vaccForKotlinVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.updateVersion
         */
        public UpdateVersionVersionAccessors getUpdateVersion() {
            return vaccForUpdateVersionVersionAccessors;
        }

    }

    public static class CatalogUpdateVersionVersionAccessors extends VersionFactory  {

        public CatalogUpdateVersionVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: catalogUpdateVersion.version (0.8.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("catalogUpdateVersion.version"); }

    }

    public static class KotlinVersionAccessors extends VersionFactory  {

        private final KotlinCouroutinesVersionAccessors vaccForKotlinCouroutinesVersionAccessors = new KotlinCouroutinesVersionAccessors(providers, config);
        public KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: kotlin.version (1.9.21)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("kotlin.version"); }

        /**
         * Returns the group of versions at versions.kotlin.couroutines
         */
        public KotlinCouroutinesVersionAccessors getCouroutines() {
            return vaccForKotlinCouroutinesVersionAccessors;
        }

    }

    public static class KotlinCouroutinesVersionAccessors extends VersionFactory  {

        public KotlinCouroutinesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: kotlin.couroutines.version (1.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("kotlin.couroutines.version"); }

    }

    public static class UpdateVersionVersionAccessors extends VersionFactory  {

        public UpdateVersionVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: updateVersion.version (0.50.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("updateVersion.version"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for catalogUpdate to the plugin id 'nl.littlerobots.version-catalog-update'
             * with versionRef 'catalogUpdateVersion.version'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCatalogUpdate() { return createPlugin("catalogUpdate"); }

            /**
             * Creates a plugin provider for kotlinJvm to the plugin id 'org.jetbrains.kotlin.jvm'
             * with versionRef 'kotlin.version'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlinJvm() { return createPlugin("kotlinJvm"); }

            /**
             * Creates a plugin provider for versionUpdate to the plugin id 'com.github.ben-manes.versions'
             * with versionRef 'updateVersion.version'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getVersionUpdate() { return createPlugin("versionUpdate"); }

    }

}
