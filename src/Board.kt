import CellRelation.NEIGHBOUR
import CellState.*

class Board {
    private val cells = mutableListOf<Cell>()

    fun addCell(cell: Cell) {
        addCellOrUpdateState(cell)
        addNeighboursOfCell(cell)
    }

    private fun addNeighboursOfCell(cell: Cell) {
        cell.neighbours().forEach {
            addCellUnlessExists(it)
        }
    }

    private fun addCellUnlessExists(cell: Cell) {
        if (missing(cell)) addNewCell(cell)
    }

    private fun addCellOrUpdateState(cell: Cell) {
        if (missing(cell)) addNewCell(cell)
        else updateCellState(cell)
    }

    private fun missing(cell: Cell) = !cells.contains(cell)

    private fun addNewCell(cell: Cell) {
        cells.add(cell)
    }

    private fun updateCellState(cell: Cell) {
        findCell(cell)?.state = cell.state
    }

    private fun findCell(cell: Cell) = cells.find { it == cell }

    fun evolve() {
        aliveNeighbours().forEach {
            val cell = it.key
            val aliveNeighboursCount = it.value
            cell.evolve(aliveNeighboursCount)
        }
    }

    private fun aliveNeighbours(): MutableMap<Cell, NeighboursCount> {
        val aliveNeighbours = mutableMapOf<Cell, NeighboursCount>()
        cells.forEach { aliveNeighbours[it] = aliveNeighboursCountFor(it) }
        return aliveNeighbours
    }

    private fun aliveNeighboursCountFor(cell: Cell): NeighboursCount {
        return NeighboursCount(aliveCells().count {
            it.relate(cell) == NEIGHBOUR
        })
    }

    fun getCellAt(coordinate: Coordinate): Cell? {
        return cells.find { it.coordinate == coordinate }
    }

    fun aliveCells(): List<Cell> {
        return cells.filter { it.state == ALIVE }
    }
}