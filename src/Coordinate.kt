import CellRelation.*

data class Coordinate(val x: Int, val y: Int) {
    fun relate(other: Coordinate): CellRelation {
        if (x == other.x && y == other.y) return SAME
        if (x - other.x in -1..1 &&
            y - other.y in -1..1) return NEIGHBOUR
        return FARAWAY
    }

    fun neighbours(): List<Coordinate> {
        return (-1..1).flatMap { i ->
            (-1..1).map { j ->
                Coordinate(x + i, y + j)
            }
        }
    }
}