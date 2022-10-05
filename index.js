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
export let rootTag = 1;

const HelloWorld = props => {
  console.log('props', props);
  rootTag = props.rootTag;
  return (
    <View style={styles.container}>
      <Text style={styles.hello}>Hello, World1</Text>
      <Text style={styles.hello}>
        Message from Rn: {props.message_from_native}
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
