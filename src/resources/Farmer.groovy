package resources

import traits.FeedsTile

/**
 * Created by Juri on 21.10.2015.
 */

class Farmer extends PopUnit implements FeedsTile {
    
    @Override
    def produce(){
        return feedTile(2) >= 0 ? feedTile(2) : 0
    }

}