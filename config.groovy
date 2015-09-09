import groovy.json.JsonSlurper


String repo = 'stefanomasini/romannumerals'

URL branchUrl = "https://api.github.com/repos/$repo/branches".toURL()
List branches = new JsonSlurper().parse(branchUrl.newReader())


def jobNames = []

branches.each { branch ->
    String jobName = "Stefano Masini numerals DSL / ${branch.name}"
    jobNames.add(jobName)
    job(jobName) {
        scm {
            github repo, branch.name
        }
        steps {
            shell "./gradlew core:publishRomanLibPublicationToMavenRepository"
            shell "./gradlew test:cleanTest test:test"
        }
    }
}


listView("Stefano's Jobs DSL") {
    description("DSL generated view for Stefano's jobs")
    jobs {
        jobNames.each { name(it) }
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
