package it.dani.learn

import it.dani.tablut.data.Move
import it.dani.tablut.data.TablutBoard
import it.dani.tablut.think.StateActionMemory

class LearningEpisode(private val memory: StateActionMemory) {

    private var actionHistory : MutableList<Pair<TablutBoard, Move>> = ArrayList()
    fun playOneMove(board: TablutBoard) : Move {
        val action = this.memory.getGreedyMove(board, EPSILON_LEARNING)
        this.actionHistory += board to action
        return action
    }

    fun setPredefinedHistory(history : List<Pair<TablutBoard, Move>>) {
        this.memory.addMoves(history)
        this.actionHistory = ArrayList()
        this.actionHistory.addAll(history)
    }

    fun rewardEpisode(reward : Int) {
        this.memory.rewardAction(this.actionHistory,reward)
    }

    companion object {
        const val EPSILON_LEARNING = 0.9
    }
}