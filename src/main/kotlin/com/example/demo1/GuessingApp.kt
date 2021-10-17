package com.example.demo1

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.util.*

class HelloApplication : Application() {
    private lateinit var controller: HelloController
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(
            HelloApplication::class.java
                .getResource("guessing-game.fxml")
        )


        val scene = Scene(fxmlLoader.load())

        stage.title = "Guess The Number!!"
        stage.scene = scene
        stage.icons.add(
            Image(
                HelloApplication::class.java
                    .getResourceAsStream("guess-icon.png")
            )
        )
        stage.isResizable = false
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}
