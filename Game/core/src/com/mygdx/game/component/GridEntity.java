package com.mygdx.game.component;

import com.mygdx.game.entity.Entity;
import com.mygdx.game.utility.Constants;
import com.mygdx.game.utility.Grid;
import com.mygdx.game.utility.Grid.GridSquare;

public class GridEntity extends Component {
	private int gridType;
	private GridSquare currSquare;

	/**
	 * A GridEntity component will add the Entity to the grid and maintain it.
	 * @param owner The Entity owner of this component.
	 * @param name The name of this component.
	 * @param active If this component is active or not.
	 * @param gridType The type of component. Note: use the Constants class to find a type for this component. 
	 * it will be in the form of Constants.GRID_STATIC... etc.
	 */
	public GridEntity(Entity owner, String name, int type, boolean active, int gridType) {
		super(owner, name, type, active);
		
		this.gridType = gridType;
		this.currSquare = Grid.addEntityToGrid(this.owner);
		
		//TODO Figure out how to disable this if static entity.
	}
	
	/**
	 * A simplified constructor for this component. Will set a generic name and active to true.
	 * @param owner The Entity owner of this component.
	 * @param gridType The type of component. Note: use the Constants class to find a type for this component. 
	 * it will be in the form of Constants.GRID_STATIC... etc.
	 */
	public GridEntity(Entity owner, int gridType){
		this(owner, "GridEntity", 0, true, gridType);
	}
	
	@Override
	public void update(float delta){
		if(this.gridType == Constants.GRID_MOVING)
			this.currSquare = Grid.updateGridSquare(this.currSquare, this.owner);
	}
	
	@Override
	public void destroy(){
		Grid.removeEntityFromGrid(this.owner); //Remove this from the grid.
		
		super.destroy(); //This goes after because this will remove the owner Entity.
	}

}
