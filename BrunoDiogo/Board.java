import java.util.Scanner;

public class Board{
	char tray[][];
	int verifyCol[];

	//construtor
	public Board(){
		verifyCol = new int[7];
		tray = new char[6][7];

		for(int i = 0; i < 6; i++){
			tray[i] = ("-------").toCharArray();
			verifyCol[i] = 5;
		}
		verifyCol[6] = 5;
	}

	//copiar tabuleiro
	public Board(Board b){
		verifyCol = new int[7];
		tray = new char[6][7];

		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++)
				tray[i][j] = b.tray[i][j];
				verifyCol[i] = b.verifyCol[i];
		}
		verifyCol[6] = b.verifyCol[6];
	}

	public void printBoard(){

		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++)
				System.out.print(tray[i][j]);
		System.out.println();
		}
	}

	public void makeMoves(char player,int col){

		if(verifyCol[col] >= 0){
			int row = verifyCol[col];
			tray[row][col] = player;
			verifyCol[col]--;
		}
	}

	public void unmakeMove(int col){
		for(int i = 0; i < 6; i++){
			if(tray[i][col] != '-'){
				tray[i][col] = '-';
				verifyCol[col]++;
				break;
			}
		}
	}

	//verificacao de no terminal
	public int isTerminal(char pcPiece, char playerPiece){
		int scorePc = 0;
		int scorePlayer = 0;

		for(int i = 5; i >= 0; i--){
			for(int j = 0; j < 7; j++){
				if(this.tray[i][j] == '-')
					continue;
				//verificar pecas direita
				if(j < 4){
					for(int l = 0; l < 4; l++){
						if(this.tray[i][j + l] == pcPiece)
							scorePc++;
						else if(this.tray[i][j + l] == playerPiece)
							scorePlayer++;
						else
							break;
					}
					if(scorePc == 4)
						return 1;
					else if(scorePlayer == 4)
						return 2;
				}

				scorePlayer = 0;
				scorePc = 0;
				//verificar pecas cima
				if(i > 2){
					for(int l = 0; l < 4; l++){
						if(this.tray[i - l][j] == pcPiece)
							scorePc++;
						else if(this.tray[i - l][j] == playerPiece)
							scorePlayer++;
						else 
							break;
					}
					if(scorePc == 4)
						return 1;
					else if(scorePlayer == 4)
						return 2;
				}

				scorePlayer = 0;
				scorePc = 0;
				//verificar diagonal direita
				if(j < 4 && i > 2){
					for(int l = 0; l < 4; l++){
						if(this.tray[i - l][j + l] == pcPiece)
							scorePc++;
						else if(this.tray[i - l][j + l] == playerPiece)
							scorePlayer++;
						else
							break;
					}
					if(scorePc == 4)
						return 1;
					else if(scorePlayer == 4)
						return 2;
				}

				scorePlayer = 0;
				scorePc = 0;
				//verificar diagonal esquerda
				if(j > 2 && i > 2){
					for(int l = 0; l < 4; l++){
						if(this.tray[i - l][j - l] == pcPiece)
							scorePc++;
						else if(this.tray[i - l][j - l] == playerPiece)
							scorePlayer++;
						else
							break;
					}
					if(scorePc == 4)
						return 1;
					else if(scorePlayer == 4)
						return 2;
				}
				scorePlayer = 0;
				scorePc = 0;
			}
		}

		for(int i = 0; i < 6; i++)
			if(this.verifyCol[i] >= 0)
				return -1;
		return 0;	
	}
}