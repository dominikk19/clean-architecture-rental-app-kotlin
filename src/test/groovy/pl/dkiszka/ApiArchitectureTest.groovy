package pl.dkiszka

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaModifier
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@AnalyzeClasses(
        packages = rootPackage,
        importOptions = [ImportOption.DoNotIncludeArchives.class,
                ImportOption.DoNotIncludeJars,
                ImportOption.DoNotIncludeTests])
class ApiArchitectureTest {

    static final DescribedPredicate<JavaClass> responseDtoClasses = JavaClass.Predicates.simpleName("ResponseDto")
    static final String rootPackage = "pl.dkiszka"

    @ArchTest
    static final ArchRule controllers_should_be_annotated_with_rest_controller =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("${rootPackage}.api")
                    .and()
                    .haveSimpleNameEndingWith("Endpoint")
                    .and()
                    .doNotHaveModifier(JavaModifier.ABSTRACT)
                    .should()
                    .beAnnotatedWith(RestController.class)


    @ArchTest
    static final ArchRule controllers_methods_should_be_return_dto_class =
            ArchRuleDefinition.methods()
                    .that()
                    .areAnnotatedWith(PostMapping.class)
                    .or()
                    .areAnnotatedWith(PutMapping.class)
                    .or()
                    .areAnnotatedWith(PatchMapping.class)
                    .or()
                    .areAnnotatedWith(GetMapping.class)
                    .and()
                    .areDeclaredInClassesThat().resideInAPackage("${rootPackage}.api")
                    .and()
                    .areDeclaredInClassesThat().haveSimpleNameEndingWith("Endpoint")
                    .and()
                    .areDeclaredInClassesThat().doNotHaveModifier(JavaModifier.ABSTRACT)
                    .should()
                    .haveRawReturnType(responseDtoClasses)

}
