import spock.lang.*

class HelloSpec extends Specification {
    def "implement me"() {
        given:        
        def hello = 'Hello!'

        expect:
        hello == 'Goodbye!'
    }
}

