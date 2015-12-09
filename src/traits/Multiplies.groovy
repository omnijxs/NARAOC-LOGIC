package traits

/**
 * Created by jxs on 2.12.2015.
 */
trait Multiplies implements Probability {

    /** Assumptions: I am implemented by an object which implements PopUnit-interface.
      * I need it to for the state property which tells my probabilities to multiply. */
    
    def multiply(){

        // TODO work with syntax!
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