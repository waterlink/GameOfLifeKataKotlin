import CellState.ALIVE
import CellState.DEAD
import NeighboursCountComparison.*

data class Cell(val coordinate: Coordinate) {
    var state: CellState = ALIVE

    fun evolve(neighboursCount: NeighboursCount) {
        if (neighboursCount.compare(NeighboursCount(2)) == LESS) {
            state = DEAD
        }

        if (neighboursCount.compare(NeighboursCount(3)) == MORE) {
            state = DEAD
        }

        if (neighboursCount.compare(NeighboursCount(3)) == EQUAL) {
            state = ALIVE
        }
    }

    fun relate(other: Cell): CellRelation {
        return coordinate.relate(other.coordinate)
    }

    fun withState(state: CellState): Cell {
        this.state = state
        return this
    }

    fun neighbours(): List<Cell> {
        return coordinate.neighbours().map { coordinate ->
            Cell(coordinate).withState(DEAD)
        }
    }
}

