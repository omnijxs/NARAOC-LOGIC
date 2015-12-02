package traits

/**
 * Created by jxs on 2.12.2015.
 */
trait Probability {

    final int max = 100

    Boolean getProbability(Integer probability){
        return getComparison(probability, (Math.random() * max))
    }

    /** Structured like this because of testability */
    def getComparison(Integer a, Integer b){
        return a >= b
    }

}