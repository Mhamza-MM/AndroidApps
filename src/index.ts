import { registerPlugin } from '@capacitor/core';

import type { AndroidAppsPlugin } from './definitions';

const AndroidApps = registerPlugin<AndroidAppsPlugin>('AndroidApps', {
  web: () => import('./web').then(m => new m.AndroidAppsWeb()),
});

export * from './definitions';
export { AndroidApps };
