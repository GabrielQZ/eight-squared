
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private String name;
    public Knight (BoardPlace startPos) {
        super(startPos);
        name = "Knight";

    }
    
    public Knight (BoardPlace startPos, boolean isWhite) {
        super(startPos, isWhite);
        name = "Knight";

    }
    
    @Override
    public Piece clone(BoardPlace pos, boolean isWhite) {
      return new Knight(pos, isWhite);
    }

    @Override
    public ChessTurn move(BoardPlace moving) {
        return new ChessTurn(this, moving);
    }

    @Override
    public String toString() {
        return (this.getIsWhite() ? "W" : "B") + "H";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BoardPlace[] getPossibleMoves( Board board ) {
        Piece movingPiece = this;

        List<BoardPlace> movesList = new ArrayList<BoardPlace>();
        boolean isWhite = movingPiece.isWhite;
        BoardPlace piecePos = movingPiece.position;

        int tempRow = piecePos.row;
        int tempCol = piecePos.columnInt;

        for (int i = -1; i <= 1; i+=2) {
            int val1 = 2;
            for (int val2 = -1; val2 <= 1; val2 += 2) {
                for (int flipped = 0; flipped <=1 ; flipped++) {
                    int xVal = flipped == 0 ? val1 * i : val2 * i;
                    int yVal = flipped == 0 ? val2 * i : val1 * i;

                    BoardPlace possibleMove = board.getSpace(tempRow + xVal, tempCol + yVal);
                    if (possibleMove != null && (possibleMove.isEmpty() || possibleMove.hasEnemy(isWhite))) {
                        movesList.add(possibleMove);
                    }
                }
            }
        }
        return movesList.toArray( new BoardPlace[0] );
    }
}
