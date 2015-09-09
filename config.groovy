import groovy.json.JsonSlurper


String repo = 'stefanomasini/romannumerals'

URL branchUrl = "https://api.github.com/repos/$repo/branches".toURL()
List branches = new JsonSlurper().parse(branchUrl.newReader())


listView("Stefano's Jobs DSL") {
    description("DSL generated view for Stefano's jobs")
    jobs {
        branches.each { branch -> name("Stefano Masini numerals DSL / ${branch.name}") }
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}


branches.each { branch ->
    job("Stefano Masini numerals DSL / ${branch.name}") {
        scm {
            github repo, branch.name
        }
        steps {
            shell "./gradlew core:publishRomanLibPublicationToMavenRepository"
            shell "./gradlew test:cleanTest test:test"
        }
    }
}


