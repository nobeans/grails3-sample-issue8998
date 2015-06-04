package grails3.blank.sample

class Book {

    String title
    String content
    String remarks

    static constraints = {
        title blank: false
        content blank: true
        remarks()
    }
}
