import spock.lang.Specification
import traits.Consumes


/**
 * Created by jxs on 26.5.2016.
 */
class ConsumesSpec extends Specification implements Consumes {

    def "Test consumes-method surplus behaviour."(){

        when:
        def surplus = consume(value)

        then:
        surplus == expected

        where:
        value     | expected
        // 0      | 0
        // null   | 0
        1         | 0
        2         | 1
        3         | 2
    }

}