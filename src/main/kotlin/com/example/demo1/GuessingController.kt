package com.example.demo1

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField

import java.net.URL
import java.util.ResourceBundle

class HelloController : Initializable {
    @FXML
    lateinit var promptLabel: Label
    lateinit var attemptsCounterLabel: Label
    lateinit var hintLabel: Label
    lateinit var confirmButton: Button
    lateinit var numberInputField: TextField
    lateinit var resetButton: Button

    var isNumberGiven = false
    var ctr = 0
    var numberToGuess: Int = -1

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        resetButton.isDisable = true
        confirmButton.setOnAction {
            // making sure input field isn't empty
            if(numberInputField.text.isEmpty() || numberInputField.text.isBlank()) {
                alertUser("Empty Input!")
                return@setOnAction
            }

            // checking for NumberFormatException
            try {
                if ('.' in numberInputField.text)
                    throw NumberFormatException("No decimals allowed.")
                numberInputField.text.toInt()
            } catch (e: NumberFormatException) {
                numberInputField.text = null

                if (e.message != null)
                    alertUser(e.message!!)
                else
                    alertUser("Invalid Input!\nNumber not found.")
                return@setOnAction
            }

            // if passes
            if (!isNumberGiven) {
                numberToGuess = numberInputField.text.toInt()

                attemptsCounterLabel.text = "Attempts: 0"
                promptLabel.text = "Guess the number!"

                numberInputField.text = null
                isNumberGiven = true
                resetButton.isDisable = false
            } else {
                val userGuess = numberInputField.text.toInt()

                if (numberToGuess == userGuess) {
                    hintLabel.text = "Congratulations!!\nYou have guessed in ${++ctr} guesses."
                    attemptsCounterLabel.text = null
                    confirmButton.isDisable = true
                    return@setOnAction
                }
                else if (numberToGuess > userGuess) {
                    hintLabel.text = "Guess too low."
                }
                else if (numberToGuess < userGuess){
                    hintLabel.text = "Guess too high."
                }
                attemptsCounterLabel.text = "Attempts: ${++ctr}"

            }
        }

        resetButton.setOnMouseClicked {
            isNumberGiven = false
            hintLabel.text = null
            numberInputField.text = null
            promptLabel.text = "Enter number and confirm number."
        }
    }

    private fun alertUser(msg: String) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Error Occurred."
        alert.headerText = "Invalid Input."
        alert.contentText = msg
        alert.show()
    }
}