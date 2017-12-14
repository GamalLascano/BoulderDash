package game.model.element.entity.actor;

import game.controller.PlaySound;
import game.model.element.Position;
import game.model.element.ElementChar;
import game.model.element.cell.Dirt;
import game.model.element.cell.Exit;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Rock;
import game.model.map.MapElement;
import game.model.map.MapInstance;

/**
 * Esta clase es la que contiene al personaje principal: Rockford Contiene su
 * puntuacion, sus diamantes y si esta empujando o no, ademas de las
 * propiedades de actores
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
		this.reset();
	}

	/**
	 * Singleton de Rockford.
	 * 
	 * @return Rockford
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
	 * Resetea a Rockford.
	 */
	public void reset()
	{
		this.setSpritechar(ElementChar.R);
		this.lives = 3;
		this.score = 0;
		this.diamonds = 0;
		this.isPushing = false;
		this.putPassables();
	}
	
	/**
	 * Retorna el score obtenido en el mapa actual.
	 * 
	 * @return score obtenido en el mapa actual
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * Retorna los diamantes obtenidos en el mapa actual.
	 * 
	 * @return diamantes obtenidos en el mapa actual
	 */
	public Integer getDiamonds()
	{
		return diamonds;
	}

	/**
	 * Retorna las vidas del jugador.
	 * 
	 * @return las vidas del jugador
	 */
	public Integer getLives()
	{
		return lives;
	}

	/**
	 * Retorna si Rockford esta empujando algo.
	 * 
	 * @return si Rockford esta empujando algo
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
			this.dying();
		}
		removeEntity(this);
	}
	
	/**
	 * Rockfrod muere y explota.
	 */
	private void dying()
	{
		decrementLives();
		saveScore();
		PlaySound.explosion();
		this.explode();
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
			this.digging(dirt);
		}
		else
		{
			this.walking();
		}
	}
	
	/**
	 * Rockfrod cava.
	 */
	private void digging(Dirt dirt)
	{
		PlaySound.dig();
		dirt.removeDirt();
	}
	
	/**
	 * Rockford camina.
	 */
	private void walking()
	{
		PlaySound.step();
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
			this.collecting(diamond);
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
		if (playerInExit(door))
		{
			addScore();
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
		MapElement.removeElement(getPosition());
		this.makeMove();
		MapElement.setActor(this);
	}

	@Override
	public void makeMove()
	{
		switch (state)
		{
			case MOVINGUP:
				makeMoveUp();
				this.setSpritechar(ElementChar.n);
				break;
			case MOVINGDOWN:
				makeMoveDown();
				this.setSpritechar(ElementChar.u);
				break;
			case MOVINGRIGHT:
				makeMoveRight();
				this.setSpritechar(ElementChar.b);
				break;
			case MOVINGLEFT:
				makeMoveLeft();
				this.setSpritechar(ElementChar.d);
				break;
			case IDLE:
				this.collect(MapElement.getDiamond(getPosition()));
				this.setSpritechar(ElementChar.R);
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
			this.dig(MapElement.getDirt(getPosition()));
			this.collect(MapElement.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapElement.getDiamond(getPosition()));
		}
	}

	@Override
	public void makeMoveDown()
	{
		if (this.canGoDown())
		{
			getPosition().goDown();
			this.dig(MapElement.getDirt(getPosition()));
			this.collect(MapElement.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapElement.getDiamond(getPosition()));
		}
	}

	@Override
	public void makeMoveRight()
	{
		if (this.canGoRight())
		{
			getPosition().goRight();
			this.dig(MapElement.getDirt(getPosition()));
			this.collect(MapElement.getDiamond(getPosition()));
		}
		else if (itemRightMoveable())
		{
			this.push(MapElement.getRock(getPosition().checkRight(), getPosition().getY()));
		}
		else
		{
			this.collect(MapElement.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 * @return si el item derecho es un moveable
	 */
	private boolean itemRightMoveable()
	{
		if(MapElement.getItem(getPosition().checkRight(), getPosition().getY()) != null)
		{
			return MapElement.getItem(getPosition().checkRight(), getPosition().getY()).isMoveable();
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return si el item izquierdo es un moveable
	 */
	public boolean itemLeftMoveable()
	{
		if(MapElement.getItem(getPosition().checkLeft(), getPosition().getY()) != null)
		{
			return MapElement.getItem(getPosition().checkLeft(), getPosition().getY()).isMoveable();
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public void makeMoveLeft()
	{
		if (this.canGoLeft())
		{
			getPosition().goLeft();
			this.dig(MapElement.getDirt(getPosition()));
			this.collect(MapElement.getDiamond(getPosition()));
		}
		else if (itemLeftMoveable())
		{
			this.push(MapElement.getRock(getPosition().checkLeft(), getPosition().getY()));
		}
		else
		{
			this.collect(MapElement.getDiamond(getPosition()));
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
					this.pushingright(rock);
					this.dig(MapElement.getDirt(getPosition()));
					this.collect(MapElement.getDiamond(getPosition()));
				}
				break;
			case MOVINGLEFT:
				if (rock != null && rock.isMoveable() && rock.canGoLeft())
				{
					this.pushingleft(rock);
					this.dig(MapElement.getDirt(getPosition()));
					this.collect(MapElement.getDiamond(getPosition()));
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
		this.getPassable().put(ElementChar.C.hashCode(), ElementChar.C);
		this.getPassable().put(ElementChar.D.hashCode(), ElementChar.D);
		this.getPassable().put(ElementChar.X.hashCode(), ElementChar.X);
		this.getPassable().put(ElementChar.e.hashCode(), ElementChar.e);
	}
	
	/**
	 * Recolecta el diamante.
	 */
	private void collecting(Diamond diamond)
	{
		PlaySound.diamond();
		diamonds++;
		diamond.collected();
		if (!Exit.getInstance().isOpen())
		{
			score += MapInstance.getInstance().getDiamondvalue();
		}
		else
		{
			score += MapInstance.getInstance().getDiamondbonus();
		}
	}

	/**
	 * Decrementa una vida de rockford.
	 */
	private void decrementLives()
	{
		state = StatusActorEnum.DEAD;
		if (this.lives > 0)
		{
			this.lives--;
		}
	}
	
	/**
	 * Guarda el score, resetea el score y diamantes de rockford.
	 */
	private void saveScore()
	{
		MapInstance.getInstance().setPlayerscore(score);
		this.score = MapInstance.getInstance().getPlayerscore();
		this.diamonds = 0;
	}
	
	/**
	 * Guarda el score y lo suma, resetea el score y diamantes de rockford.
	 */
	private void addScore()
	{
		score += 1 + MapInstance.getInstance().getSelectedLevel();
		MapInstance.getInstance().setPlayerscore(score + MapInstance.getInstance().getPlayerscore());
		diamonds = 0;
	}
	
	/**
	 * 
	 * @param door
	 * @return si rockford esta en la salida
	 */
	private boolean playerInExit(Exit door)
	{
		return player.getPosition().equals(door.getPosition());
	}
	
	/**
	 * Empuja la roca a la derecha.
	 * @param rock
	 */
	private void pushingright(Rock rock)
	{
		PlaySound.push();
		isPushing = true;
		rock.pushed(this);
		isPushing = false;
		getPosition().goRight();
	}
	
	/**
	 * Empuja la roca a la izquierda.
	 * @param rock
	 */
	private void pushingleft(Rock rock)
	{
		PlaySound.push();
		isPushing = true;
		rock.pushed(this);
		isPushing = false;
		getPosition().goLeft();
	}

}
