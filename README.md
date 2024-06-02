# HorizontalCompass
Perhaps the best horizontal compass plugin for your minecraft server

## Demonstration
| display: | GIF-preview                 |
|----------|-----------------------------|
| degrees  | ![](readme-src/degrees.gif) |
| rust     | ![](readme-src/rust.gif)    |
| simple   | ![](readme-src/simple.gif)  |

## Settings (settings.yml)
```yaml
compass-settings:
  # Type of compass display. Available values: actionbar, bossbar
  type: bossbar
  # Style of compass display. Available values: degrees, simple, rust
  display: rust
  bossbar:
    # Color of the bossbar progressbar. Available colors:
    # PINK, BLUE, RED, GREEN, YELLOW, PURPLE, WHITE
    color: WHITE
    # Should the bossbar progressbar change its progress depending on
    # the player's head rotation
    progress: false

display-settings:
  degrees:
    # Color of the angles displayed on the compass (display: degrees)
    angle-color: '#FFFFFF'
    # Color of the central angle displayed on the compass (display: degrees)
    angle-color-center: '#9966CC'
  rust:
    # Color of the entire compass (display: rust)
    color: '#FFFFFF'

waypoint-settings:
  # Color of the cardinal directions display (N, NE, E, ...)
  cardinal-color: '#00FFFF'
  # Symbol used to mark the home point on the compass
  home-marker: '▼'

# Display home points from Essentials on the compass?
essentials-home: true
```

## Optional(Soft) Dependencies
- Essentials - houses, displaying their points on the compass
- ProtocolLib (recommended) - update the compass when you turn your head

## License
HorizontalCompass is licensed under the GNU General Public License v3.0. See [COPYING](COPYING) for more information.