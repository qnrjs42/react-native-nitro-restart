import { NitroModules } from 'react-native-nitro-modules';
import type { NitroRestart as NitroRestartSpec } from './specs/NitroRestart.nitro';

const _NitroRestartModule =
  NitroModules.createHybridObject<NitroRestartSpec>('NitroRestart');

export const restartApp = () => {
  _NitroRestartModule.restartApp();
};

export const exitApp = () => {
  _NitroRestartModule.exitApp();
};

// Export the hybrid object with hidden internal methods
export const NitroRestartModule: Omit<
  NitroRestartSpec,
  'dispose' | '__type' | 'equals' | 'name' | 'toString'
> = _NitroRestartModule;
