import CellRelation.*
import CellState.ALIVE
import CellState.DEAD
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CellTest {
    private var aliveCell = Cell(Coordinate(0, 0))
    private var deadCell = Cell(Coordinate(0, 0))

    @Before
    fun setUp() {
        aliveCell = Cell(Coordinate(5, 5))
        assertEquals(ALIVE, aliveCell.state)

        deadCell = Cell(Coordinate(5, 5))
        deadCell.state = DEAD
        assertEquals(DEAD, deadCell.state)
    }

    @Test
    fun testCellDiesWithLessThanTwoNeighbours() {
        aliveCell.evolve(NeighboursCount(0))
        assertEquals(DEAD, aliveCell.state)
    }

    @Test
    fun testCellStaysAliveWithExactlyTwoNeighbours() {
        aliveCell.evolve(NeighboursCount(2))
        assertEquals(ALIVE, aliveCell.state)
    }

    @Test
    fun testCellStaysAliveWithExactlyThreeNeighbours() {
        aliveCell.evolve(NeighboursCount(3))
        assertEquals(ALIVE, aliveCell.state)
    }

    @Test
    fun testCellDiesWithMoreThanFourNeighbours() {
        aliveCell.evolve(NeighboursCount(4))
        assertEquals(DEAD, aliveCell.state)
    }

    @Test
    fun testCellComesToLifeWithExactlyThreeNeighbours() {
        deadCell.evolve(NeighboursCount(3))
        assertEquals(ALIVE, deadCell.state)
    }

    @Test
    fun testNeighbourOfOtherCell() {
        val cell = Cell(Coordinate(5, 5))
        val neighbour = Cell(Coordinate(5, 6))
        assertEquals(NEIGHBOUR, cell.relate(neighbour))
    }

    @Test
    fun testFarawayFromOtherCell() {
        val cell = Cell(Coordinate(5, 5))

        val farawayCellByX = Cell(Coordinate(25, 6))
        assertEquals(FARAWAY, cell.relate(farawayCellByX))

        val farawayCellByY = Cell(Coordinate(4, 26))
        assertEquals(FARAWAY, cell.relate(farawayCellByY))
    }

    @Test
    fun testSameCell() {
        val cell = Cell(Coordinate(5, 5))
        assertEquals(SAME, cell.relate(cell))
    }
}

