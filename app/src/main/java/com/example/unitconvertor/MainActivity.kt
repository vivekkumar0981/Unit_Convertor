package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitconvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitconvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // this greeting function is the start point where we call a greeting function and pass android which is a composable form
                    // and it show text with hello android in it

                   Unitconvertor()
                }
            }
        }
    }
}

// we can make our own composable function so that we can do stuffs on it
/*
@Composable
fun Unitconvertor()
{
    // so bassicall we are ussing a composable function call collumn which give the output top to bottom while we call a function greeting
    Column {
      //  Greeting("Android")
       // Greeting("Android")
        
    }
    Row {
        // so basically this also a composable function called row which give the out in side to each other 
        Greeting(name = "vivek")
        Greeting(name = "ridhima")
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!  ",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitconvertorTheme {
        Greeting("Android")
    }
}
*/
// modifier allignment krne ke liye color ko thik krne ke liye
// mdofifere size area place sb ussi se hota hai hai



@Composable
fun Unitconvertor()
{


    // inputvalue is use to take the input from the user and then display it what they are entering
    var inputvalue by remember{ mutableStateOf("")}
    var iexpanded by remember{ mutableStateOf(false)}
    var outexpanded by remember{ mutableStateOf(false)}
    var inputunit by remember{ mutableStateOf("Metre")}
    var outputunit by remember{ mutableStateOf("Metre")}
    val conversionFactor =remember{ mutableDoubleStateOf(1.00 )}
    val oconversionFactor = remember { mutableStateOf(1.00)}
    var result :Double =0.0
    val outputunitval =remember{ mutableDoubleStateOf(100.0) }

    var outputValue by remember { mutableStateOf("0.00")}

    fun conversion()
    {

        // ?: this is called elvis operator it is same as ternary operator


        var value=(inputvalue.toDoubleOrNull() ?: 0.01)
        result =(value * conversionFactor.doubleValue*100.0 / oconversionFactor.value ).roundToInt()/100.0
        outputValue=result.toString()


    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement =Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Unit convertor", fontWeight = FontWeight(450), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // this is bassically the function text is use to pring the string on the screen



        // outline textfield is use to make a box like structure and where we can take imut from the user

        // inputvalue=it is use to show the input click to the user interface

        OutlinedTextField(
            value = inputvalue,
            onValueChange = {
                inputvalue=it
                conversion() },
            label = { Text(text = "Enter the value")},
            )

        Spacer(modifier = Modifier.height(16.dp))

     //  OutlinedTextField(value ="", onValueChange ={} , placeholder = { Text(text = "hello")})


        //the composable function label is use in to hint in the box but while clicking on it moved above it
        // while using placeholder function we can see only hind until the area is not filled with text



        Row {

            // local context.cuurent is use to define so that we can use toast function

            // on click is use to defice on cliking the button what should happen in it
            // toast length long is use to defince that it will show the text for long period
            // or we can use toast short to get the value

          //  val content= LocalContext.current


         /*   Button(onClick = { Toast.makeText(content,"thanks for clicking me!!",Toast.LENGTH_LONG).show() }) {

                Text(text = "click me")

            }

        }
        */
            // spacer and padding is use to give space in all direction to the tex,button,anything
            // spacer is use to do it in all direction
            // for specific side padding we use padding
            // as we have done it in button which is inside the button

            // input box

            Box {

                //input button


                Button(onClick = { iexpanded=true}, modifier = Modifier.padding(8.dp,0.dp)) {

                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="" )

                    // now we are making drop down menu for it

                    DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded=false }) {

                        DropdownMenuItem(
                            text = { Text(text = "Centimeter") },
                            onClick = {
                                iexpanded=false
                                inputunit="centimeter"
                                conversionFactor.value=0.01




                            })

                        DropdownMenuItem(
                            text = { Text(text = "Meter") },
                            onClick = {
                                iexpanded=false
                                inputunit="Meter"
                                conversionFactor.value=1.0




                            })

                        DropdownMenuItem(
                            text = { Text(text = "Feet") },
                            onClick = {
                                iexpanded=false
                                inputunit="Feet"
                                conversionFactor.value=0.3048




                            })

                        DropdownMenuItem(
                            text = { Text(text = "Millimeter") },
                            onClick = {
                                iexpanded=false
                                inputunit="Millimeter"
                                conversionFactor.value=0.001





                            })

                        
                    }

                }
            }

            // output box
            Box {

                // output button


                Button(onClick = { outexpanded=true }, modifier = Modifier.padding(8.dp,0.dp)) {
                    Text(text =outputunit )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                    DropdownMenu(expanded = outexpanded, onDismissRequest = { outexpanded=false }) {

                        DropdownMenuItem(
                            text = { Text(text = "Centimeter") },
                            onClick = {
                                outexpanded=false
                                outputunit="centimeter"
                                oconversionFactor.value=0.01




                                conversion() })

                        DropdownMenuItem(
                            text = { Text(text = "Meter") },
                            onClick = {
                                outexpanded=false
                                outputunit="Meter"
                                oconversionFactor.value=1.0



                                conversion()
                            })

                        DropdownMenuItem(
                            text = { Text(text = "Feet") },
                            onClick = {
                                outexpanded=false
                                outputunit="Feet"
                                oconversionFactor.value=0.3048
                                conversion()
                            })

                        DropdownMenuItem(
                            text = { Text(text = "Millimeter") },
                            onClick = {
                                outexpanded=false
                                outputunit="Millimeter"
                                oconversionFactor.value=0.001

                                conversion()
                            })

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result : $outputValue $outputunit",style = MaterialTheme.typography.headlineLarge)

    }
}

@Preview(showBackground = true)
@Composable
fun Theunitconvertor()
{
    Unitconvertor()
}
