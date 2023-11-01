package pl.dkiszka

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition

@AnalyzeClasses(
        packages = rootPackage,
        importOptions = [ImportOption.DoNotIncludeArchives.class,
                ImportOption.DoNotIncludeJars,
                ImportOption.DoNotIncludeTests])
class ApplicationArchitectureTest {

    static final String rootPackage = "pl.dkiszka"

    @ArchTest
    static final ArchRule application_should_be_free_of_cycles =
            SlicesRuleDefinition.slices()
                    .matching("${rootPackage}.(*)..")
                    .should()
                    .beFreeOfCycles()

    @ArchTest
    static final ArchRule application_should_be_developed_in_layered_architecture =
            Architectures.layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("Api").definedBy("..${rootPackage}.api..")
                    .layer("Application").definedBy("..${rootPackage}.application..")
                    .layer("Domain").definedBy("..${rootPackage}.domain..")
                    .layer("Infrastructure").definedBy("..${rootPackage}.infrastructure..")
                    .whereLayer("Api").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Application").mayOnlyBeAccessedByLayers("Api", "Infrastructure")
                    .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Api")

}
