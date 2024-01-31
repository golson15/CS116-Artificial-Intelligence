package edu.westmont.cs116;

/**
 * The class that does the work of searching for the best split in the data
 * @author Grace Olson
 *
 */
public class MachineLearning {
	
	/**
	 * 
	 * @param instances The data to evaluate
	 * @return the split that generates the highest accuracy when applied to the instances provided
	 */

	public static Split searchForSplit(Instances instances) {
		// TODO: Implement the search for the best split	
	
		Split bestSplit = new Split(SplitOperator.ALWAYS_TRUE, 0 , 9.0);
		//make an initial split
		
		
		for(Instance instance : instances.getInstances()) {
		//iterate through all rows of the data
			for(SplitOperator splitOp : SplitOperator.values()) {
			//iterate through all operator options
				for(int i = 0; i < instances.numberOfFields(); i++) {
				//iterate through all fields
					Split split = new Split(splitOp, i , instance.getField(i));
					if (instances.calculateAccuracy(split) > instances.calculateAccuracy(bestSplit)) {
						bestSplit = split;
						//if the combo of operator, field, and value have a greater accuracy than previous one,
						//then change bestSplit to this split
					}
				}
			}
		}
		
		return bestSplit;
		
	}

}

