import NeighboursCountComparison.*

class NeighboursCount(val count: Int) {
    fun compare(other: NeighboursCount): NeighboursCountComparison {
        if (count < other.count) return LESS
        if (count > other.count) return MORE
        return EQUAL
    }

}