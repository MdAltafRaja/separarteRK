import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import {name as appName} from './app.json';
import TestConnectNative from './src/TestConnectNative/TestConnectNative';
import Payment from './src/Payment';
import {StripeProvider} from './src/stripeHook/myStripe';

export let rootTag = 1;

const HelloWorld = props => {
  console.log('props', props);
  rootTag = props.rootTag;
  return (
    <View style={styles.container}>
      <StripeProvider
        publishableKey="pk_test_51LaG9HSCUK2fnqXLspxwy6k7V5hgqxP3csUhPSt3HrpYEbcI2DtKBeK9uPgSxGRD7wTJi4QZMSK5xdKF2AayY70W00XNIxQwC3"
        //  urlScheme="your-url-scheme" // required for 3D Secure and bank redirects
        //  merchantIdentifier="merchant.com.{{YOUR_APP_NAME}}" // required for Apple Pay
      >
        <Payment />
      </StripeProvider>

      <Text style={styles.hello}>
        Message from ReactNative: {props.message_from_native}
      </Text>
      <TouchableOpacity
        onPress={_ => {
          TestConnectNative.exitRN(rootTag);
        }}
        style={{backgroundColor: 'red'}}>
        <Text style={styles.hello}>Exit RN</Text>
      </TouchableOpacity>
    </View>
  );
};
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent(appName, () => HelloWorld);
