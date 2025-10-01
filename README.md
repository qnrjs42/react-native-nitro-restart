# react-native-nitro-restart

Restart or exit your React Native app programmatically. Built with [Nitro Modules](https://github.com/mrousavy/nitro).

## Installation

```bash
npm install @qnrjs42/react-native-nitro-restart
# or
yarn add @qnrjs42/react-native-nitro-restart
# or
pnpm add @qnrjs42/react-native-nitro-restart
```

Then run pod install for iOS:

```bash
cd ios && pod install
```

## Usage

```typescript
import { restartApp, exitApp } from '@qnrjs42/react-native-nitro-restart';

// Restart the app
restartApp();

// Exit the app
exitApp();
```

That's it.

## API

### `restartApp()`

Restarts your React Native app.

- iOS: Uses React Native's built-in reload mechanism
- Android: Relaunches the main activity with a fresh task

### `exitApp()`

Exits the application.

- iOS: Suspends and exits after 0.5s
- Android: Kills the process cleanly

## Examples

### Restart after language change

```typescript
import { restartApp } from '@qnrjs42/react-native-nitro-restart';
import AsyncStorage from '@react-native-async-storage/async-storage';

const changeLanguage = async (lang: string): Promise<void> => {
  await AsyncStorage.setItem('language', lang);
  restartApp();
};
```

### Exit on logout

```typescript
import { exitApp } from '@qnrjs42/react-native-nitro-restart';

const logout = async (): Promise<void> => {
  await clearUserData();
  exitApp();
};
```

## Requirements

- React Native >= 0.70
- iOS >= 13.0
- Android >= 21
- react-native-nitro-modules (peer dependency)

## Why Nitro?

Nitro provides direct native calls without the React Native bridge, making this library fast and type-safe with zero overhead.

## License

MIT
