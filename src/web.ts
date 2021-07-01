import { WebPlugin } from '@capacitor/core';

import type { AndroidAppsPlugin } from './definitions';

export class AndroidAppsWeb extends WebPlugin implements AndroidAppsPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
