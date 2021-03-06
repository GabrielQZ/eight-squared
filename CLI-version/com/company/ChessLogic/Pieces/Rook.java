package com.company.ChessLogic.Pieces;

import com.company.ChessLogic.Enviorment.Board;
import com.company.ChessLogic.Enviorment.BoardPlace;
import com.company.ChessLogic.Control.Turn;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private String name;
    public Rook (BoardPlace startPos) {
        super(startPos);
        name = "Rook";
    }
    @Override
    public Turn move(BoardPlace moving) {
        return new Turn(this, moving);
    }

    @Override
    public BoardPlace[] getPossibleMoves( Board board) {
        Piece movingPiece = this;

        List<BoardPlace> movesList = new ArrayList<>();
        boolean isWhite = movingPiece.isWhite;
        BoardPlace piecePos = movingPiece.position;

        int tempRow = piecePos.row;
        int tempCol = piecePos.columnInt;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ( (i == 0 || j == 0) && !( i == 0 && j == 0) ) {
                    boolean hitBlock = false;
                    int blocksMoved = 1;
                    while  (!hitBlock) {
                        BoardPlace possibleMove = board.getSpace(tempRow + blocksMoved * i, tempCol + blocksMoved * j );
                        if (possibleMove != null && possibleMove.isEmpty() ) {
                            movesList.add(possibleMove);
                            blocksMoved++;
                        } else if (possibleMove != null && possibleMove.hasEnemy(isWhite)) {
                            movesList.add(possibleMove);
                            hitBlock = true;
                        } else hitBlock = true;
                    }
                }
            }
        }

        return movesList.toArray( new BoardPlace[0] );
    }

    @Override
    public String toString() {
        return (this.isWhite ? "W" : "B") + "R";
    }

    @Override
    public String getName() {
        return name;
    }
}
