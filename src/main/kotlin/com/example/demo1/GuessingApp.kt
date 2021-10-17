package com.example.demo1

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    private lateinit var controller: HelloController
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(
            HelloApplication::class.java.getResource("guessing-game.fxml")
        )


        val scene = Scene(fxmlLoader.load())

        stage.title = "Guess The Number!!"
        stage.scene = scene
        stage.isResizable = false
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}