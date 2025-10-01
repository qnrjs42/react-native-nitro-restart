import type { HybridObject } from 'react-native-nitro-modules';

export interface NitroRestart
  extends HybridObject<{ ios: 'swift'; android: 'kotlin' }> {
  restartApp(): void;
  exitApp(): void;
}
