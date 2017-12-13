package game.model.element;

/**
 * Este enum contiene los caracteres de identificacion de cada elemento
 * para la representacion en la consola de todas los elementos del juego.<p>
 * 	Rockford (d,b,u,n animaciones)<br>
 *	R, d, b, u, n,<br>
 *	Enemies (F: firefly, B: butterfly)<br>
 *	F, B, <br>
 *	Cells (E,e salida cerrada y abierta)<br>
 *	D, T, W, w, E, e, <br>
 *	Items (O: rocas, X: diamante, A: amoeba, _: vacia)<br>
 *	O, X, A, _, <br>
 */
public enum ElementChar
{
	//Rockford (d,b,u,n animaciones)
	R, d, b, u, n,
	//Enemies (F: firefly, B: butterfly)
	F, B, 
	//Cells (E,e salida cerrada y abierta)
	D, T, W, w, E, e, 
	//Items (O: rocas, X: diamante, A: amoeba, _: vacia)
	O, X, A, _,
}
