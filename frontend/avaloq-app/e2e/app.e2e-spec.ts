import { AvaloqAppPage } from './app.po';

describe('avaloq-app App', function() {
  let page: AvaloqAppPage;

  beforeEach(() => {
    page = new AvaloqAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
