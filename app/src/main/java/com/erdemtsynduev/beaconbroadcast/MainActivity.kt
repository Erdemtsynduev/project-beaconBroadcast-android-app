package com.erdemtsynduev.beaconbroadcast

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.erdemtsynduev.beaconbroadcast.ui.theme.BeaconBroadcastTheme
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.BeaconTransmitter
import java.util.Arrays

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeaconBroadcastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    startAdvertising(applicationContext)
                }
            }
        }
    }
}

const val RADIUS_NETWORK_MANUFACTURER = 0x0118
const val DEFAULT_TX_POWER = -59

private fun startAdvertising(context: Context) {
    val beacon = Beacon.Builder()
        .setBluetoothName("Test")
        .setId1("C467D3B6-8102-FE9E-161D-478ED75D19BE")
        .setId2("1")
        .setId3("2")
        .setManufacturer(RADIUS_NETWORK_MANUFACTURER)
        .setDataFields(listOf(0L))
        .setTxPower(DEFAULT_TX_POWER)
        .build()
    val beaconParser =
        BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")
    val beaconTransmitter = BeaconTransmitter(context, beaconParser)
    beaconTransmitter.startAdvertising(beacon)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeaconBroadcastTheme {
        Greeting("Android")
    }
}