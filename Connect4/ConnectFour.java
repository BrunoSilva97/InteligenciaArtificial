import java.util.Scanner;
import java.lang.Integer;

class ConnectFour{

	public static char pcPiece;
	public static char playerPiece;
	public static int totalNodes;

	private static void choosePiece(Scanner in){
	
		System.out.println("Escolha a sua peca (X ou O): ");
		playerPiece = in.next().charAt(0);
		while(true){
			if(playerPiece == 'X' || playerPiece == 'O')
				break;
			System.out.println("Peca errada!");
			System.out.println("Escolha a sua peca (X ou O): ");
			playerPiece = in.next().charAt(0);
		}
		if(playerPiece == 'X')
			pcPiece = 'O';
		else
			pcPiece = 'X';
	}

	public static int utility(Board b, int points){
		int score = 0;
		int scorePc = 0;
		int scorePlayer = 0;
		int terminal = b.isTerminal(pcPiece, playerPiece);

		if(terminal == 0)
			return 0;
		else if(terminal == 1)
			return 512; //pc ganha
		else if(terminal == 2)
			return -512; // jogador ganha

		//jogo nao terminado
		for(int i = 5; i >= 0; i--){
			for(int j = 0; j < 7; j++){
				if(b.tray[i][j] == '-')
					continue;
				//ver direita
				if(j < 5){
					for(int l = 0; l < 3; l++){
						if(b.tray[i][j + l] == pcPiece)
							scorePc++;
						else
							break;
					}
					score = score + tableScore(scorePc);	
				}

				scorePc = 0;
				scorePlayer = 0;
				//ver cima
				if( i >= 2){
					for(int l = 0; l < 3; l++){
						if(b.tray[i - l][j] == pcPiece)
							scorePc++;
						else
							break;
					}
					score = score + tableScore(scorePc);
				}

				scorePc = 0;
				scorePlayer = 0;
				//ver diagonal direita
				if(j < 5 && i >= 2){
					for(int l = 0; l < 3; l++){
						if(b.tray[i - l][j + l] == pcPiece)
							scorePc++;
						else
							break;
					}
					score = score + tableScore(scorePc);
				}

				scorePc = 0;
				scorePlayer = 0;
				//ver diagonal esquerda
				if(j >= 2 && i >= 2){
					for(int l = 0; l < 3; l++){
						if(b.tray[i - l][j - l] == pcPiece)
							scorePc++;
						else
							break;
					}
					score = score + tableScore(scorePc);
				}
			}
		}
		return score + points;
	}

	public static int tableScore(int score){
		switch(score){
			case 0: return 0;
			case 1: return 1;
			case 2: return 10;
			case 3: return 50;
		}
		return 0;
	}

	public static Board minMax(Board b){
		int value;
		int max = Integer.MIN_VALUE;
		int maxP = 0;

		System.out.println();
		totalNodes = 0;
		System.out.println("PC a pensar...");
		double iniTempo = System.currentTimeMillis();

		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			totalNodes++;
			Board temp = new Board(b);
			temp.makeMoves(pcPiece, i);
			value = min(1,temp);
			if(value > max){
				max = value;
				maxP = i;
			}
		}

		double finTempo = System.currentTimeMillis();
		System.out.println("Numero total de nos: " + totalNodes);
		System.out.println("Tempo da procura: " + (finTempo - iniTempo)/1000 + "s");
		System.out.println("Movimento do PC: " + (maxP + 1));
		b.makeMoves(pcPiece, maxP);
		System.out.println();
		return b;
	}

	//MAX
	public static int max(int depth, Board b){
		if(depth == 9 || b.isTerminal(pcPiece, playerPiece) != -1){
			return utility(b,-16);
		}
		int value = Integer.MIN_VALUE;
		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			Board temp = new Board(b);
			totalNodes++;
			temp.makeMoves(pcPiece, i);
			value = Math.max(value,min(depth + 1, temp));
		}
		return value;
	}

	//MIN
	public static int min(int depth, Board b){
		if(depth == 9 || b.isTerminal(pcPiece, playerPiece) != -1){
			return utility(b,16);
		}
		int value = Integer.MAX_VALUE;
		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			Board temp = new Board(b);
			totalNodes++;
			temp.makeMoves(playerPiece, i);
			value = Math.min(value,max(depth + 1, temp));
		}
		return value;	
	}

	public static Board playerMoves(Board b, Scanner in){
		int move;
		System.out.println();
		System.out.println("Turno do jogador " + playerPiece);
		System.out.println("Escolha a coluna para jogar (1-7):");
		move = in.nextInt();

		while(move < 1 || move > 7 || b.tray[0][move - 1] != '-'){
			System.out.println("Impossivel fazer esse movimento");
			System.out.println("Turno do jogador " + playerPiece);
			System.out.println("Escolha a coluna para jogar (1-7):");
			move = in.nextInt();
		}
		b.makeMoves(playerPiece, move - 1);
		b.printBoard();
		return b;
	}

	public static Board alfaBeta(Board b){
		int value;
		int max = Integer.MIN_VALUE;
		int maxP = 0;

		System.out.println();
		totalNodes = 0;
		System.out.println("PC a pensar...");
		System.out.println();
		double iniTempo = System.currentTimeMillis();

		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			totalNodes++;
			Board temp = new Board(b);
			temp.makeMoves(pcPiece, i);
			value = min(1,temp,Integer.MIN_VALUE,Integer.MAX_VALUE);
			if(value > max){
				max = value;
				maxP = i;
			}
		}

		double finTempo = System.currentTimeMillis();
		System.out.println("Numero total de nos: " + totalNodes);
		System.out.println("Tempo da procura: " + (finTempo - iniTempo)/1000 + "s");
		System.out.println("Movimento do PC: " + (maxP + 1));
		b.makeMoves(pcPiece, maxP);
		System.out.println();
		return b;
	}

	//MAX alfaBeta
	public static int max(int depth, Board b,int alfa, int beta){
		if(depth == 9 || b.isTerminal(pcPiece, playerPiece) != -1){
			return utility(b,-16);
		}
		int value = Integer.MIN_VALUE;
		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			Board temp = new Board(b);
			totalNodes++;
			temp.makeMoves(pcPiece, i);
			value = Math.max(value,min(depth + 1, temp,alfa,beta));
			if(value >= beta)
				return value;
			alfa = Math.max(alfa,value);
		}
		return value;
	}

	//MIN alfaBeta
	public static int min(int depth, Board b,int alfa,int beta){
		if(depth == 9 || b.isTerminal(pcPiece, playerPiece) != -1){
			return utility(b,16);
		}
		int value = Integer.MAX_VALUE;
		for(int i = 0; i < 7; i++){
			if(b.verifyCol[i] < 0)
				continue;
			Board temp = new Board(b);
			totalNodes++;
			temp.makeMoves(playerPiece, i);
			value = Math.min(value,max(depth + 1, temp,alfa,beta));
			if(value <= alfa)
				return value;
			beta = Math.min(beta,value);
		}
		return value;	
	}

		public static void Game(Board b, Scanner in, int n){

		
			choosePiece(in);

			int flag = 0;
			int terminal = 0;
			char resposta;

			System.out.println("Quer jogar primeiro?(s/n)");
			resposta = in.next().charAt(0);
			if(resposta == 's' || resposta == 'n')
				flag = 1;

			while(flag == 0){

				System.out.println("Opcao errada!");
				System.out.println("Quer jogar primeiro?(s/n)");
				resposta = in.next().charAt(0);
				if(resposta == 's' || resposta == 'n')
					flag = 1;
			}

			if(resposta == 's'){
				System.out.println();
				b.printBoard();
				playerMoves(b, in);			
			}

			while(true){
				if(n == 1){
					b = minMax(b);
				}
				else if(n == 2){
					b = alfaBeta(b);
				}
				System.out.println("Tabuleiro atual");
				b.printBoard();

				terminal = b.isTerminal(pcPiece, playerPiece);

				if(terminal == 1){
					System.out.println();
					System.out.println("PC ganhou!");
					return;
				}
				
				if(terminal == 0){
					System.out.println();
					System.out.println("Jogo empatado!");
					return;
				}

				b = playerMoves(b, in);

				terminal = b.isTerminal(pcPiece, playerPiece);

				if(terminal == 2){
					System.out.println();
					System.out.println("Jogador ganhou!");
					return;
				}

				System.out.println();
			}
		}	

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		System.out.println("Escolha o algoritmo que pretende usar:");
		System.out.println("Prima 1 para MinMax");
		System.out.println("Prima 2 para AlfaBeta");
		int n = in.nextInt();
		while((n != 1) && (n != 2)){
			System.out.println("Opcao errada! Volte a tentar");
			n = in.nextInt();
		}
		Board b = new Board();
		Game(b,in,n);
	}
}