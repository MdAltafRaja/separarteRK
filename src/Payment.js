import React, {useState} from 'react';
import {Alert, Button, Text, TextInput, View} from 'react-native';
//import {useStripe} from '@stripe/stripe-react-native';
import {useStripe} from './stripeHook/myStripe';

const Payment = () => {
  const [name, setName] = useState([]);
  const stripe = useStripe();
  const subscribe = async () => {
    try {
      // const response = await fetch('http://localhost:8080/pay', {
      //   method: 'POST',
      //   body: JSON.stringify({name}),
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      // });
      // console.log('inside fetch');
      // const data = await response.json();
      // if (!response.ok) Alert.alert(`response.ok ${data.message}`);
      // const clientSecret = data.clientSecret;
      const clientSecret = 'data.clientSecret';
      const initSheet = await stripe.initPaymentSheet({
        paymentIntentClientSecret: clientSecret,
      });
      console.log('aashu', initSheet);
      if (initSheet.error)
        return Alert.alert(
          `initSheet.error ${JSON.stringify(data)} ${initSheet.error.message}`,
        );
      const presentSheet = await stripe.presentPaymentSheet({clientSecret});
      console.log('aashu2', presentSheet);
      if (presentSheet.error)
        return Alert.alert(`presentSheet.error ${presentSheet.error.message}`);

      Alert.alert('payment complete');
    } catch (err) {
      console.log(err);
      Alert.alert('something went wrong');
    }
  };
  return (
    <View
      style={{
        justifyContent: 'center',
        alignItems: 'center',
        flex: 1,
      }}>
      <TextInput
        onChangeText={text => setName(text)}
        value={name}
        placeholder="name"
        style={{width: 300, fontSize: 20, padding: 10, borderWidth: 1}}
      />
      <Button
        title="pay"
        onPress={ev => {
          subscribe();
        }}></Button>
    </View>
  );
};

export default Payment;
