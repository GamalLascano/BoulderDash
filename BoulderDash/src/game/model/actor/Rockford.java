package game.model.actor;

import game.model.ListOfEntities;
import game.model.SpriteChar;
import game.model.cell.Dirt;
import game.model.cell.Exit;
import game.model.item.Diamond;
import game.model.item.Rock;
import game.model.map.MapActor;
import game.model.map.MapCell;
import game.model.map.MapInstance;
import game.model.map.MapItem;
import game.model.map.MapVisual;

/**
 * Esta clase es la que contiene al personaje principal: Rockford Contiene un
 * spritechar para representacion visual, su puntuacion, sus diamantes y si esta
 * empujando o no, ademas de las otras propiedades de otros actores
 */
public class Rockford extends Actor
{
	private SpriteChar spritechar;
	private Integer score;
	private Integer lives;
	private Integer diamonds;
	private boolean isPushing;
	private static Rockford player;

	/**
	 * 
	 */
	private Rockford()
	{
		super(null);
		this.spritechar = SpriteChar.R;
		this.lives = 0;
		this.score = 0;
		this.diamonds = 0;
		this.isPushing = false;
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
		this.getPassable().put(SpriteChar.D.hashCode(), SpriteChar.D);
		this.getPassable().put(SpriteChar.X.hashCode(), SpriteChar.X);
		this.getPassable().put(SpriteChar.e.hashCode(), SpriteChar.e);
	}

	/**
	 * 
	 * @return
	 */
	public static Rockford getInstance()
	{
		if (player == null)
		{
			player = new Rockford();
		}
		return player;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Rockford getRockford()
	{
		return player;
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getDiamonds()
	{
		return diamonds;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getLives()
	{
		return lives;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPushing()
	{
		return isPushing;
	}

	/**
	 * 
	 * @param points
	 */
	public void setScore(int points)
	{
		this.score = points;
	}

	/**
	 * 
	 * @param diamond
	 */
	public void setDiamonds(int diamond)
	{
		this.diamonds = diamond;
	}

	/**
	 * 
	 * @param pushing
	 */
	public void setPushing(boolean pushing)
	{
		this.isPushing = pushing;
	}
	
	/**
	 * 
	 * @param lives
	 */
	public void setLives(int lives)
	{
		this.lives = lives;
	}

	/**
	 * 
	 */
	public void die()
	{
		if (state != StatusActorEnum.DEAD)
		{
			state = StatusActorEnum.DEAD;
			if (this.lives > 0)
				this.lives--;
			this.diamonds = 0;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(getPosition());
	}

	/**
	 * 
	 * @return
	 */
	public boolean save()
	{
		return true;
	}

	/**
	 * Remueve la tierra del juego
	 * 
	 * @param dirt:
	 *            Bloque de tierra
	 */
	public void dig(Dirt dirt)
	{
		if (dirt != null)
		{
			dirt.removeDirt();
		}
	}

	/**
	 * Si es un diamante, lo recolecta
	 * 
	 * @param diamond: Bloque de diamante
	 */
	public void collect(Diamond diamond)
	{
		if (diamond != null && diamond.isCollectable())
		{
			diamonds++;
			diamond.collected();
			if(!Exit.getInstance().isOpen())
			{
				score+= MapInstance.getDiamondvalue();
			}
			else
			{
				score+= MapInstance.getDiamondbonus();
			}
		}
	}

	/**
	 * 
	 */
	public void changePosition()
	{
		MapActor.removeActor(getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}

	/**
	 * Se occupa de mover a Rockford en la matriz, tambien verifica si la celda
	 * destino es solida para moverse. Rockford cava automaticamente la tierra.
	 * 
	 */
	public boolean isInExit()
	{
		Exit door = Exit.getInstance();
		if (player.getPosition().equals(door.getPosition()))
		{
			score+= 1 + MapInstance.getSelectedLevel();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 */
	public void makeMove()
	{
		switch (state)
		{
			case MOVINGUP:
				makeMoveUp();
				spritechar = SpriteChar.n;
				break;
			case MOVINGDOWN:
				makeMoveDown();
				spritechar = SpriteChar.u;
				break;
			case MOVINGRIGHT:
				makeMoveRight();
				spritechar = SpriteChar.b;
				break;
			case MOVINGLEFT:
				makeMoveLeft();
				spritechar = SpriteChar.d;
				break;
			case IDLE:
				this.collect(MapItem.getDiamond(getPosition()));
				spritechar = SpriteChar.R;
				break;
			default:
				break;
		}
		state = StatusActorEnum.IDLE;
	}

	/**
	 * 
	 */
	public void makeMoveUp()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkUp()).hashCode()))
		{
			getPosition().goUp();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveDown()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode()))
		{
			getPosition().goDown();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveRight()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkRight(), this.getPosition().getY()).hashCode()))
		{
			getPosition().goRight();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else if (MapItem.getItem(getPosition().checkRight(), getPosition().getY()).isMoveable() == true)
		{
			this.push(MapItem.getRock(getPosition().checkRight(), getPosition().getY()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveLeft()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkLeft(), this.getPosition().getY()).hashCode()))
		{
			getPosition().goLeft();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else if (MapItem.getItem(getPosition().checkLeft(), getPosition().getY()).isMoveable() == true)
		{
			this.push(MapItem.getRock(getPosition().checkLeft(), getPosition().getY()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}
	
	/**
	 * Este metodo hace que si esta empujando a una roca, se le ponga el estado
	 * correspondiente
	 * 
	 * @param rock:Bloque de roca
	 */
	public void push(Rock rock)
	{
		switch (state)
		{
			case MOVINGRIGHT:
				if (rock != null && rock.isMoveable() && rock.getPassable().containsKey(MapVisual.getChar(rock.getPosition().checkRight(), rock.getPosition().getY()).hashCode()))
				{
					isPushing = true;
					rock.pushed(this);
					isPushing = false;
					getPosition().goRight();
					this.dig(MapCell.getDirt(getPosition()));
					this.collect(MapItem.getDiamond(getPosition()));
				}
				break;
			case MOVINGLEFT:
				if (rock != null && rock.isMoveable() && rock.getPassable().containsKey(MapVisual.getChar(rock.getPosition().checkLeft(), rock.getPosition().getY()).hashCode()))
				{
					isPushing = true;
					rock.pushed(this);
					isPushing = false;
					getPosition().goLeft();
					this.dig(MapCell.getDirt(getPosition()));
					this.collect(MapItem.getDiamond(getPosition()));
				}
				break;
			default:
				break;
		}
	}


}
