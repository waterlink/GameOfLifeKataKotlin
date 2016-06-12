import CellState.ALIVE
import CellState.DEAD
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BoardTest() {
    var board = Board()

    @Before
    fun setUp() {
        board = Board()
    }

    @Test
    fun testBoardWithOneCellHasNoCellsAfterEvolution() {
        board.addCell(Cell(Coordinate(5, 5)))
        val cell = board.getCellAt(Coordinate(5, 5))
        assertEquals(ALIVE, cell?.state)
        board.evolve()
        assertEquals(DEAD, cell?.state)
    }

    @Test
    fun testBoardWithThreeNeighbourCellsEvolvesIntoRectangle() {
        board.addCell(Cell(Coordinate(5, 5)))
        board.addCell(Cell(Coordinate(5, 6)))
        board.addCell(Cell(Coordinate(6, 5)))

        val cellBeforeEvolution = board.getCellAt(Coordinate(6, 6))
        assertEquals(DEAD, cellBeforeEvolution?.state)

        board.evolve()

        val cellAfterEvolution = board.getCellAt(Coordinate(6, 6))
        assertEquals(ALIVE, cellAfterEvolution?.state)
    }

    @Test
    fun testBoardWithRectangleStaysSameAfterEvolution() {
        board.addCell(Cell(Coordinate(5, 5)))
        board.addCell(Cell(Coordinate(5, 6)))
        board.addCell(Cell(Coordinate(6, 5)))
        board.addCell(Cell(Coordinate(6, 6)))

        board.evolve()

        assertEquals(listOf(
                Cell(Coordinate(5, 5)),
                Cell(Coordinate(5, 6)),
                Cell(Coordinate(6, 5)),
                Cell(Coordinate(6, 6))
        ), board.aliveCells())
    }

    @Test
    fun testBoardWithColumnChangesAxis() {
        board.addCell(Cell(Coordinate(5, 5)))
        board.addCell(Cell(Coordinate(5, 6)))
        board.addCell(Cell(Coordinate(5, 7)))

        board.evolve()

        assertEquals(listOf(
                Cell(Coordinate(4, 6)),
                Cell(Coordinate(5, 6)),
                Cell(Coordinate(6, 6))
        ), board.aliveCells())

        board.evolve()

        assertEquals(listOf(
                Cell(Coordinate(5, 5)),
                Cell(Coordinate(5, 6)),
                Cell(Coordinate(5, 7))
        ), board.aliveCells())
    }
}