import java.util.Scanner;
import java.util.TreeSet;

class Hash{
	TreeSet<String> neighbor;

	Hash(){
		this.neighbor = new TreeSet<String>();
	}
}

public class Config{
	int pos[][];
	int level;
	int cost;
	char play;
	String path;
	public int xPos;
	public int yPos;

	//Contrutor sem argumentos
	Config(){
		this.pos = new int[4][4];
		this.level = 0;
		this.cost = 0;
		this.play = 'N';
		this.path = "";
	}

	//Contrutor a partir de outra Config
	Config(int level, char play, String path, Config node){
		this.level = level;
		this.play = play;
		this.path = path;
		this.cost = node.cost;
		this.pos = new int[4][4];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				this.pos[i][j] = node.pos[i][j];
	}

	public String HashCode(){
		String code = "";
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				code = code + this.pos[i][j];
		return code;	
	}

	//ler Config
	public void readConfig(Scanner in){
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				this.pos[i][j] = in.nextInt();
	}

	//escrever Config
	public void printConfig(){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++)
				System.out.print(pos[i][j] + " ");
		System.out.println();
		}
	}

	//procurar zero
	public void findZero(){
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(pos[i][j] == 0){
					xPos = i;
					yPos = j;
					return;	
				}
		return;
	}

	public boolean solvability(){
		int row[] = new int[16];
		int aux;
		int inv = 0;
		int k = 0;

		//create row from Config
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++){
				row[k] = pos[i][j];
				k++;
			}

		//count inversions
		for(int i = 0; i < 16; i++){
			aux = row[i];
			if(aux != 0)
				for(int j = i + 1; j <= 15; j++)
					if(row[j] != 0 && aux > row[j])
						inv++;
		}

		findZero();

		aux = 4 - xPos;
		if(aux % 2 != 0 && inv % 2 == 0)
      		return true;
    	else if(aux % 2 == 0 && inv % 2 != 0)
      		return true;
    	return false;

	}
}