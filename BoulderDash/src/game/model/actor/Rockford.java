package game.model.actor;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.SpriteChar;
import game.model.cell.Dirt;
import game.model.cell.Exit;
import game.model.item.Diamond;
import game.model.item.Rock;
import game.model.map.MapActor;
import game.model.map.MapCell;
import game.model.map.MapInstance;
import game.model.map.MapItem;
import game.view.sound.SoundPlay;

/**
 * Esta clase es la que contiene al personaje principal: Rockford Contiene su
 * puntuacion, sus diamantes y si esta empujando o no, ademas de las otras
 * propiedades de otros actores
 */
public class Rockford extends Actor
{
	private Integer score;
	private Integer lives;
	private Integer diamonds;
	private boolean isPushing;
	private static Rockford player;

	/**
	 * Constructor de Rockford.
	 */
	private Rockford()
	{
		super(new Position(0, 0));
		this.setSpritechar(SpriteChar.R);
		this.lives = 3;
		this.score = 0;
		this.diamonds = 0;
		this.isPushing = false;
		this.putPassables();
	}

	/**
	 * Singleton de Rockford.
	 * 
	 * @return Singleton
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
	 * Retorna a Rockford, se utiliza despues de invocar al singleton.
	 * 
	 * @return Rockford
	 */
	public static Rockford getRockford()
	{
		return player;
	}

	/**
	 * Resetea a Rockford.
	 */
	public void reset()
	{
		this.setSpritechar(SpriteChar.R);
		this.lives = 3;
		this.score = 0;
		this.diamonds = 0;
		this.isPushing = false;
		this.putPassables();
	}
	
	/**
	 * Retorna el score obtenido en el mapa actual.
	 * 
	 * @return score
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * Retorna los diamantes obtenidos en el mapa actual.
	 * 
	 * @return diamantes
	 */
	public Integer getDiamonds()
	{
		return diamonds;
	}

	/**
	 * Retorna las vidas del jugador.
	 * 
	 * @return vidas
	 */
	public Integer getLives()
	{
		return lives;
	}

	/**
	 * Retorna si Rockford esta empujando algo.
	 * 
	 * @return pushing
	 */
	public boolean isPushing()
	{
		return isPushing;
	}

	@Override
	public void die()
	{
		if (state != StatusActorEnum.DEAD)
		{
			SoundPlay.explosion();
			state = StatusActorEnum.DEAD;
			if (this.lives > 0)
			{
				this.lives--;
			}
			MapInstance.setPlayerscore(score);
			this.score = MapInstance.getPlayerscore();
			this.diamonds = 0;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(getPosition());
	}

	/**
	 * Remueve la tierra del juego.
	 * 
	 * @param dirt:
	 *            Bloque de tierra
	 */
	public void dig(Dirt dirt)
	{
		if (dirt != null && dirt.isDirty())
		{
			SoundPlay.dig();
			dirt.removeDirt();
		}
		else
		{
			SoundPlay.step();
		}
	}

	/**
	 * Si es un diamante, lo recolecta.
	 * 
	 * @param diamond:
	 *            Bloque de diamante
	 */
	public void collect(Diamond diamond)
	{
		if (diamond != null && diamond.isCollectable())
		{
			SoundPlay.diamond();
			diamonds++;
			diamond.collected();
			if (!Exit.getInstance().isOpen())
			{
				score += MapInstance.getDiamondvalue();
			}
			else
			{
				score += MapInstance.getDiamondbonus();
			}
		}
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
			score += 1 + MapInstance.getSelectedLevel();
			MapInstance.setPlayerscore(score + MapInstance.getPlayerscore());
			diamonds = 0;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void changePosition()
	{
		MapActor.removeActor(getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}

	@Override
	public void makeMove()
	{
		switch (state)
		{
			case MOVINGUP:
				makeMoveUp();
				this.setSpritechar(SpriteChar.n);
				break;
			case MOVINGDOWN:
				makeMoveDown();
				this.setSpritechar(SpriteChar.u);
				break;
			case MOVINGRIGHT:
				makeMoveRight();
				this.setSpritechar(SpriteChar.b);
				break;
			case MOVINGLEFT:
				makeMoveLeft();
				this.setSpritechar(SpriteChar.d);
				break;
			case IDLE:
				this.collect(MapItem.getDiamond(getPosition()));
				this.setSpritechar(SpriteChar.R);
				break;
			default:
				break;
		}
		state = StatusActorEnum.IDLE;
	}

	@Override
	public void makeMoveUp()
	{
		if (this.canGoUp())
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

	@Override
	public void makeMoveDown()
	{
		if (this.canGoDown())
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

	@Override
	public void makeMoveRight()
	{
		if (this.canGoRight())
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

	@Override
	public void makeMoveLeft()
	{
		if (this.canGoLeft())
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
	 * @param rock:Bloque
	 *            de roca
	 */
	public void push(Rock rock)
	{
		switch (state)
		{
			case MOVINGRIGHT:
				if (rock != null && rock.isMoveable() && rock.canGoRight())
				{
					SoundPlay.push();
					isPushing = true;
					rock.pushed(this);
					isPushing = false;
					getPosition().goRight();
					this.dig(MapCell.getDirt(getPosition()));
					this.collect(MapItem.getDiamond(getPosition()));
				}
				break;
			case MOVINGLEFT:
				if (rock != null && rock.isMoveable() && rock.canGoLeft())
				{
					SoundPlay.push();
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

	@Override
	public void rotate()
	{
		// TODO Auto-generated method stub
		
	}
	
	// METODOS SIMPLES
	
	/**
	 * Pone los passables de Rockford.
	 */
	private void putPassables()
	{
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
		this.getPassable().put(SpriteChar.D.hashCode(), SpriteChar.D);
		this.getPassable().put(SpriteChar.X.hashCode(), SpriteChar.X);
		this.getPassable().put(SpriteChar.e.hashCode(), SpriteChar.e);
	}
	

}
