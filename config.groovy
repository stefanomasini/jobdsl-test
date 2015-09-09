listView("Stefano's Jobs DSL") {
    description("DSL generated view for Stefano's jobs")
    jobs {
        name("Stefano Masini numerals DSL")
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


String repo = 'stefanomasini/romannumerals'


job("Stefano Masini numerals DSL") {
    scm {
        github repo
    }
    steps {
        shell "./gradlew core:publishRomanLibPublicationToMavenRepository"
        shell "./gradlew test:cleanTest test:test"
    }
}
