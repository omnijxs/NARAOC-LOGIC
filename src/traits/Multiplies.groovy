package traits

/**
 * Created by jxs on 2.12.2015.
 */
trait Multiplies implements Probability {

    def multiply(){

        if(this.state.resolveMultiply()){
            if(getProbability(this.state.multiplicationRate)){
                return breed()
            }
        }
    }

    def breed(){
        return this.class.newInstance()
    }

}