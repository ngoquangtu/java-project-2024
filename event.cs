using System;

/// 1
public delegate void OnGameOver();
public static event OnGameOver onGameOver;

/// 2
public static event Action OngameOver;