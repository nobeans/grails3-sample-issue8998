package grails3.blank.sample

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

    def book

    void setup() {
        book = new Book(title: "Groovy in Action", content: "Awesome", remarks: "Must buy")
    }

    @Unroll
    void "validate: '#value' of #field results in #error"() {
        given:
        book[field] = value

        when:
        book.validate()

        then:
        assertValidate book, field, error

        where:
        field     | value | error
        'title'   | null  | 'nullable'
        'title'   | ''    | 'blank'
        'title'   | 'Hi'  | 'valid'
        'content' | null  | 'nullable'
        'content' | ''    | 'valid'
        'content' | 'Hi'  | 'valid'
        'remarks' | null  | 'nullable'
        'remarks' | ''    | 'valid'
        'remarks' | 'Hi'  | 'valid'
    }

    private void assertValidate(obj, field, error) {
        def validated = obj.validate()
        if (error && error != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert error == obj.errors[field].code
        } else {
            assert !obj.errors[field]
        }
    }
}
