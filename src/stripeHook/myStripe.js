import React from 'react';
import {
  Modal,
  View,
  Text,
  Button,
  TouchableOpacity,
  TextInput,
} from 'react-native';

const SomeContext = React.createContext([null, () => {}]);

export const useStripe = () => {
  const [someVal, someFn] = React.useContext(SomeContext);
  return {
    initPaymentSheet: ({paymentIntentClientSecret}) => {
      console.log('initPaymentSheet called');
      return Promise.resolve({});
    },
    presentPaymentSheet: async ({clientSecret}) => {
      console.log('presentPaymentSheet called');

      return await someFn();
    },
  };
};

export const StripeProvider = ({publishableKey, children}) => {
  const [showSomething, setShowSomething] = React.useState(false);
  const [cardNumber, setCardNumber] = React.useState('');
  const [expireDate, setExpireDate] = React.useState('');
  const [cvv, setCvv] = React.useState('');
  const [error, setError] = React.useState(false);
  const ref = React.useRef(null);
  let value = React.useMemo(() => {
    return [
      publishableKey,
      async () => {
        console.log('will do something');
        setShowSomething(true);

        return new Promise(resolve => {
          ref.current = resolve;
          //   setTimeout(resolve, 2000);
        });
      },
    ];
  });
  const handlePress = _ev => {
    if (cardNumber === '' || cvv === '' || expireDate === '') {
      setError(_ => true);
    } else {
      setError(_ => false);
      setShowSomething(false);
      ref.current('hello');
    }
  };

  return (
    <View style={{flex: 1}}>
      <Modal
        visible={showSomething}
        animationType="slide"
        presentationStyle="pageSheet"
        transparent={false}
        modalP>
        <View style={{backgroundColor: 'white', flex: 1, padding: 20}}>
          <View
            style={{
              flexDirection: 'row',
              alignItems: 'center',
              marginBottom: 20,
            }}>
            <TouchableOpacity
              onPress={() => {
                setShowSomething(_ => false);
              }}
              style={{padding: 5, marginRight: 20}}>
              <Text style={{fontSize: 25, color: 'gray'}}>X</Text>
            </TouchableOpacity>
            <View
              style={{
                backgroundColor: 'lightyellow',
                padding: 5,
                borderRadius: 10,
                paddingHorizontal: 15,
              }}>
              <Text style={{fontSize: 20}}>Test Mode</Text>
            </View>
          </View>
          <Text style={{fontSize: 20, fontWeight: 'bold', marginBottom: 20}}>
            Add your payment information
          </Text>
          <View
            style={{
              flexDirection: 'row',
              justifyContent: 'space-between',
              paddingBottom: 5,
            }}>
            <Text style={{color: 'gray', fontSize: 15}}>Card information</Text>
            <Text style={{color: 'blue', fontSize: 15}}>Scan card</Text>
          </View>
          <View
            style={{
              borderWidth: 1,
              borderColor: 'gray',
              borderRadius: 10,
              paddingTop: 7,
              marginBottom: 20,
            }}>
            <View
              style={{
                paddingVertical: 10,
                paddingHorizontal: 10,
                borderBottomWidth: 1,
                borderColor: 'gray',
              }}>
              <TextInput
                value={cardNumber}
                onChangeText={text => {
                  setCardNumber(_ => text);
                }}
                style={{fontSize: 20}}
                placeholder="Card number"
              />
            </View>
            <View style={{flexDirection: 'row'}}>
              <View
                style={{
                  paddingVertical: 10,
                  paddingHorizontal: 10,
                  borderRightWidth: 1,
                  borderColor: 'gray',
                  width: 50 + '%',
                }}>
                <TextInput
                  value={expireDate}
                  onChangeText={text => {
                    setExpireDate(_ => text);
                  }}
                  style={{fontSize: 20}}
                  placeholder="MM / YY"
                />
              </View>
              <View
                style={{
                  paddingVertical: 10,
                  paddingHorizontal: 10,
                  width: 50 + '%',
                }}>
                <TextInput
                  value={cvv}
                  onChangeText={text => {
                    setCvv(_ => text);
                  }}
                  style={{fontSize: 20}}
                  placeholder="CVV"
                />
              </View>
            </View>
          </View>
          {!error ? (
            <></>
          ) : (
            <Text style={{color: 'red'}}>fill all the require entries</Text>
          )}
          <TouchableOpacity
            style={{
              //backgroundColor: {if(cardNumber=="" &&cvv===""&&expireDate===""){ 'lightblue'}else{"blue"}},
              backgroundColor: 'blue',
              paddingVertical: 12,
              borderRadius: 10,
              alignItems: 'center',
              justifyContent: 'center',
            }}
            onPress={handlePress}>
            <Text style={{fontSize: 20, color: 'white'}}>Pay Now</Text>
          </TouchableOpacity>
        </View>
      </Modal>

      <SomeContext.Provider value={value}>{children}</SomeContext.Provider>
    </View>
  );
};
