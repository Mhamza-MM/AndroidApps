export interface AndroidAppsPlugin {
  echo(options: {
    value: string;
  }): Promise<{
    value: AppInfo[];
  }>;
}

export interface AppInfo {
  label: string,
  info: any,
}